package polymorphism;

public class SamsungTV implements Tv {
	private Speaker speaker;
	
	
	public SamsungTV() {
		System.out.println("samsungTV 객체화()");
	}
	
	public SamsungTV(Speaker speaker) {
		this.speaker = speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	@Override
	public void powerOn() {
		System.out.println("SamsungTV === 전원 켠다.");
	}
	
	@Override
	public void powerOff() {
		System.out.println("SamsungTV === 전원 끈다.");
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
