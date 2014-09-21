
public class DataTools {
	
	public int[] stringToAsciiArray(String str){
		
		int asciis[] = new int[str.length()];

		for(int i = 0; i<str.length(); i++){
			
			
			asciis[i] = (int)(str.charAt(i));
		}
			
		return asciis;
	}
	
	public String intToFillString(int number, int length){ // wype�nia zerami liczbe od poz�tku - nie zmieniaj�c jej warto�ci
		String tmp = String.valueOf(number);
		for(int i=0; i<(length-tmp.length()+1); i++){
			tmp="0"+tmp;
		}
		
		return tmp;
	}
	
	public String stringToFillString(String number, int length){ // wype�nia zerami liczbe od poz�tku - nie zmieniaj�c jej warto�ci
		String tmp = number;
		for(int i=0; i<(length-tmp.length()+1); i++){
			tmp="0"+tmp;
		}
		
		return tmp;
	}
	
}
