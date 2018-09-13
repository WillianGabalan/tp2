package redeNeural;        //2


public class redeNeural extends Perceptron{  public redeNeural() {//1
		super();//1
	}//1


	/*class perceptron with two inputs and one output*/
		


		/*--------Attributes-----*/
		/*inputs of the perceptron*/
		/* inputs*/
		float[] inp= new float[2];//2
		
		float out1;           //2 
		
				
		/*---------------Methods---------*/
		public void putInpOut(float inp1, float inp2, float out1) {//2
			this.inp[0]=inp1;//3
			this.inp[1]=inp2;//3
			this.out1=out1;//3
		
		}//2
		 
		
			public float getInp(int keyCode) {//2
				// TODO Auto-generated method stub//2
			return inp[keyCode]; 
			}//2
			
			public float getOut() {				//2
				// TODO Auto-generated method stub//2
			return this.out1;				 //2
			}//2
			
			
			public float getDotProduct() {				//3
				// TODO Auto-generated method stub		//3
								/*return 0;				//3 */
				float r=0;										//4
				
				for(int i=0;i<2;i++) {							//4
					r+=getInp(i)*getWei(i);				//4
					}											//4
				return r;										//4
				
			}											 //3

			public int getStepFunction() {								//5
				// TODO Auto-generated method stub						//5
				//return 0;												//5
				if(getDotProduct()>=1) {										//6
					 return 1;                                                  //6
				}else return 0;												    //6
				
			}															//5

																//7

			public float e1() {													//7
				// TODO Auto-generated method stub								//7
						float erro=getOut()-getStepFunction();								//8
						return erro;														//8
			}																	//7


			public float e0() {													//7
				// TODO Auto-generated method stub								//7
				float erro=getOut()-getStepFunction();								//8
				return erro;														//8
			}																	//7

		
}//2
		 