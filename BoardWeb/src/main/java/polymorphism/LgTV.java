package polymorphism;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv")	//bean등록 <bean class="polymorphism.LgTV"></bean>와 같은 의미
public class LgTV implements Tv {
	@Autowired
	@Qualifier("tmaxSpeaker")
	private Speaker speaker;
	
	public LgTV() {
		System.out.println("lgTV 객체화()");
	}
	
	public LgTV(Speaker speaker) {
		this.speaker = speaker;
	}
	
	@Override
	public void powerOn() {
		System.out.println("LgTV === 전원 켠다.");
	}
	
	@Override
	public void powerOff() {
		System.out.println("LgTV === 전원 끈다.");
	}
	
	@Override
	public void volumeUp() {
		speaker.volumeUp();
	}
	
	@Override
	public void volumeDown() {
		speaker.volumeDown();
	}
}
