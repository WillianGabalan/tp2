package redeNeural;//1

public class Perceptron {//1
	/*class perceptron with two inputs and one output*/
	
	/*--------Attributes-----*/
	/*inputs of the perceptron*/
	/* inputs*/
	float[] wei= new float[2];//1
	float learn_rate;		//1

	public Perceptron() {//1
		wei[0]=0;//1
		wei[1]=0;//1
	}//1
	
	public void putWei(float wei0, float wei1) {//2
		// TODO Auto-generated method stub//2
		wei[0]=wei0;//3
		wei[1]=wei1;//3
	}//2
	
	public void putWei(float learning) {//2
		// TODO Auto-generated method stub//2
		learn_rate=learning;								//3
		
	}//2
	public float getWei(int keycode) {//2
		// TODO Auto-generated method stub//2
		return wei[keycode];//3
	}//2
	
	public float getLearning_rate() {			//2
		// TODO Auto-generated method stub		//2
		return learn_rate;									//3
	}	
	
	public void putLearning_rate(float learning) {	//2
		// TODO Auto-generated method stub			//2
		this.learn_rate= learning;							//3
	}	//2
}
