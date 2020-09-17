package cglib;

/**
 * 被代理的类
 * 
 * @author MoonZero
 */
public class Actor {

	public void basicAct(float money) {
		System.out.println("拿到 " + money + " 元，开始基本的表演!!");
	}

	public void wonderfulAct(float money) {
		System.out.println("拿到 " + money + " 元，开始精彩的表演!!");
	}
}
