package polymorphism;

//factory들어가면 객체생성
public class BeanFactory {
	public Object getBean(String beanName) {
		switch(beanName) {
		case "samsung":
			return new SamsungTV();
		case "lg":
			return new LgTV();
		}
		return null;
	}
}
