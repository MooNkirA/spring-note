package com.moon.springtx.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.domain.Userinfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * LobHandler 处理大对象数据基础使用测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-13 17:57
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class SpringLobHandlerTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private LobHandler lobHandler;

    /* 存储大对象数据测试 */
    @Test
    public void lobHandlerWriteTest() {
        try {
            // 准备images的字节数组
            Resource resource = new FileSystemResource("E:\\00-Downloads\\6.jpg");
            // 使用spring框架的工具类FileCopyUtils，将文件资源转成字节数组
            byte[] images = FileCopyUtils.copyToByteArray(resource.getFile());

            // 准备description
            String description = "接口类中的方法和属性不要加任何修饰符号（public 也不要加），保持代码的简洁性，并加上有效的 Javadoc 注释。尽量不要在接口里定义变量，如果一定要定义变量，确定与接口方法相关，并且是整个应用的基础常量。\n" +
                    "【参考】枚举类名带上 Enum 后缀，枚举成员名称需要全大写，单词间用下划线隔开。\n" +
                    "说明：枚举其实就是特殊的常量类，且构造方法被默认强制是私有。\n" +
                    "正例：枚举名字为 ProcessStatusEnum 的成员名称：SUCCESS / UNKNOWN_REASON。";

            // 创建userinfo对象
            Userinfo userinfo = new Userinfo();
            userinfo.setImages(images);
            userinfo.setDescription(description);

            // 插入到数据库中
            jdbcTemplate.execute("insert into userinfo(images,description)values(?,?)", new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
                // 此方法用于设置Blob类型与长文本类型
                @Override
                protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
                    lobCreator.setBlobAsBytes(ps, 1, userinfo.getImages());
                    lobCreator.setClobAsString(ps, 2, userinfo.getDescription());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /* 读取大对象数据测试 */
    @Test
    public void lobHandlerReadTest() {
        // 直接通过JdbcTemplate读取
        Userinfo userinfo1 = jdbcTemplate.queryForObject("select * from userinfo where id = ?", new BeanPropertyRowMapper<>(Userinfo.class), 1);
        System.out.println(userinfo1.getImages());
        System.out.println(userinfo1.getDescription());

        System.out.println("--------------------------");

        // 通过结果集对象处理读取
        Userinfo userinfo2 = jdbcTemplate.query("select * from userinfo where id = ?", new ResultSetExtractor<Userinfo>() {
            @Override
            public Userinfo extractData(ResultSet rs) throws SQLException, DataAccessException {
                Userinfo userinfo = null;
                if (rs.next()) {
                    userinfo = new Userinfo();
                    userinfo.setId(rs.getInt("id"));
                    userinfo.setImages(lobHandler.getBlobAsBytes(rs, "images"));
                    userinfo.setDescription(lobHandler.getClobAsString(rs, "description"));
                }
                return userinfo;
            }
        }, 1);
        System.out.println(userinfo2.getImages());
        System.out.println(userinfo2.getDescription());
    }

}
