package redeNeural;																//1

import static org.junit.Assert.assertEquals;									//1
import static org.junit.Assert.assertThat;										//1

import org.hamcrest.CoreMatchers;												//1
import org.junit.jupiter.api.Test;												//1


 class redeNeuralTest {															//1
	
	
	@Test
		//1
	void testConstrutor() {														//1
				
		redeNeural per1= new redeNeural();										//1
		per1.putWei(0.8f,0f);
		per1.putInpOut(1f,1f,0f);
		assertEquals(1f,per1.getInp(0),0.01);									//1
		assertThat(per1.getWei(0), CoreMatchers.is(CoreMatchers.equalTo(0.8f)));//1
		assertThat(per1.getWei(1), CoreMatchers.is(CoreMatchers.equalTo(0f)));	//1
		assertThat(per1.getInp(0), CoreMatchers.is(CoreMatchers.equalTo(1f)));	//1
		assertThat(per1.getInp(1), CoreMatchers.is(CoreMatchers.equalTo(1f)));	//1
		assertThat(per1.getOut(), CoreMatchers.is(CoreMatchers.equalTo(0f)));	//1
		}//1
	


	@Test//3
	void testDotProduct() {															//3
				
		redeNeural per1= new redeNeural();
		per1.putWei(0.8f,0f);														//3
		per1.putInpOut(1f,1f,0f);													//3
	    /*... r= 0*0,9 + 0,1*0,8 = 0,08 .... */										//3
		
		assertEquals(0.8f,per1.getDotProduct(),0.001);								//3
		}																			//3

	@Test																				//5
	void testStepFunction() {															//5
				
		redeNeural per1= new redeNeural();												//5
	    assertEquals(0f,per1.getStepFunction(),0.001f);									//5
		}																				//5
	
	
	@Test																					//6
	void testPesoSeguinte(){																//6
			redeNeural opc01 = new redeNeural();											//6
			redeNeural opc02 = new redeNeural();											//6
			redeNeural opc03 = new redeNeural();											//6
			redeNeural opc04 = new redeNeural(); 											//6
			
			opc01.putWei(0.5f,0f);															//6
			opc01.putLearning_rate(0.2f);														//6
			opc01.putInpOut(0f,0f,0f);														//6
			
			opc02.putInpOut(0f,1f,0f);														//6	
			
			opc03.putInpOut(1f,0f,0f);														//6
			
			opc04.putInpOut(1f,1f,1f);														//6
			
			float aux01 = opc01.e0()*opc01.getLearning_rate()*opc01.getInp(0);				//6
			float aux02 = opc02.e0()*opc01.getLearning_rate()*opc02.getInp(0);				//6
			float aux03 = opc03.e0()*opc01.getLearning_rate()*opc03.getInp(0);				//6
			float aux04 = opc04.e0()*opc01.getLearning_rate()*opc04.getInp(0);				//6
			
			float aux11 = opc01.e1()*opc01.getLearning_rate()*opc01.getInp(1);				//6
			float aux12 = opc02.e1()*opc01.getLearning_rate()*opc02.getInp(1);				//6
			float aux13 = opc03.e1()*opc01.getLearning_rate()*opc03.getInp(1);				//6
			float aux14 = opc04.e1()*opc01.getLearning_rate()*opc04.getInp(1);				//6
			
			opc01.putWei(opc01.getWei(0)+aux01+aux02+aux03+aux04, opc01.getWei(1)+aux11+aux12+aux13+aux14);	//6		
			
			assertEquals(0.7f,opc01.getWei(1),0.001f);										//6
			assertEquals(0.2f,opc01.getWei(0),0.001f);										//6
			
	}																						//6

	
 }																					//1
