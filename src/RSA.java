import java.math.BigInteger;
import java.util.Random;


public class RSA {

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		
		DataTools tools = new DataTools();
		int sizeOfAsciiReprezentation = 4;
		

		Random rnd = new Random();
	       
	     BigInteger p = new BigInteger("33478071698956898786044169848212690817704794983713768568912431388982883793878002287614711652531743087737814467999489");
	     BigInteger q = new BigInteger("36746043666799590428244633799627952632279158164343087642676032283815739666511279233373417143396810270092798736308917");
	     BigInteger n=p.multiply(q);

	     //funkcja eulera : eul(n) = (p-1)(q-1)
	     BigInteger eul_n = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
	     
	     Boolean flag = true;
	     BigInteger e;
	     BigInteger d = new BigInteger("0");
	     do{	    
	     //wybieramy liczbê e (1 < e < eul(n)) wzglêdnie pierwsz¹ z eul(n)
		     do{
		        do {	        	
		        	e = new BigInteger(eul_n.bitLength(), rnd);
		        } while (e.compareTo(eul_n) >= 0);
		     }while (NWD(e,eul_n).compareTo(new BigInteger("1")) == 0); //najmniejszy wspólny dzielnik - compareTo zwraca 0 gdy poprawnie
	
		   //d=e^(-1) mod eul(n) 
		     try{
		    	 d = e.modInverse(eul_n); //czasami zg³asza sie b³¹d zwi¹zany z dzieleniem przez 0
		    	 flag=true;
		     }catch(Exception exc){
		    	 flag=false;
		     }
	     }while(!flag);
	     
	     
	     System.out.println("P="+p.toString());
	     System.out.println("q="+q.toString());
	     System.out.println("e="+e.toString());
	     System.out.println("l.length"+n.toString().length());
	     System.out.println("n="+n.toString());
	     System.out.println("eul(n)="+eul_n.toString());
	     System.out.println("d="+d.toString());
	     	     
	     //int signsNumberPerBlock = n.toString().length()-1/sizeOfAsciiReprezentation;
	     int signsNumberPerBlock = 9; //bior¹c pod uwagê 4 cyfrow¹ reprezentacjê ascii tylko 5 reprezentacji mieœci siê w zmiennej typu long
	     
	     
	     
	     
	     String message = "niesamowita przygoda jasia w kopalni...";
	   
	     
	     System.out.print("\n WIADOMOŒÆ:\n"+message+"\n\n");
	     int asciis[] = tools.stringToAsciiArray(message);
	     
	     //definicja tablicy bloków
	     int rest = asciis.length%signsNumberPerBlock;
	     int numberOfBlocks = asciis.length/signsNumberPerBlock;
	     if(rest>0) numberOfBlocks++;
	     String blocks[] = new String[numberOfBlocks-1];
	     blocks[0] = "";
	     
	     int counter = 0;
	     int currentBlock = 0;
	     
	     System.out.print("\n Bloki: \n");
	     
	     for(int i = 0; i<asciis.length; i++){
	    	 
	    	 String sign = tools.intToFillString(asciis[i],sizeOfAsciiReprezentation);
    		 System.out.print("x"+sign+"  ");
	    	 blocks[currentBlock]+= sign;
	    	 
	    	 if(counter<signsNumberPerBlock){
	    		 counter++; 
	    	 }else{
	    		 System.out.print("\n");
	    		 currentBlock++;
	    		 blocks[currentBlock] = "";
	    		 counter=0;
	    	 } 
	    	 
	     }
	     
	     //parsowanie do zmiennych long
	     System.out.print("\n\n BLOKI JAKO LICZBY LONG: \n");
	     BigInteger l_blocks[] = new BigInteger[blocks.length];
	     
	     for(int i=0; i<blocks.length; i++){
	    	 l_blocks[i] = new BigInteger(blocks[i]);
	    	 System.out.print(l_blocks[i].toString()+"\n");
	     }
	     
	     //---------------------------------------------------------------szyfrowanie
	     System.out.print("\n ZASZYFROWANE BLOKI: \n");
	     BigInteger zaszyfrowane[] = new BigInteger[blocks.length];
	     for(int i=0; i<blocks.length; i++){
	    	 zaszyfrowane[i] = l_blocks[i].modPow(e, n);
	    	 System.out.print(zaszyfrowane[i].toString()+"\n");
	     }
	     
	     //---------------------------------------------------------------odszyfrowywanie
	     System.out.print("\n\n ODSZYFROWANE BLOKI: \n");
	     BigInteger odszyfrowane[] = new BigInteger[blocks.length];
	     for(int i=0; i<blocks.length; i++){
	    	 odszyfrowane[i] = zaszyfrowane[i].modPow(d, n);
	    	 System.out.print(odszyfrowane[i].toString()+"\n");
	     }
	     
	     //---------------------------------------------------------------sk³adanie tablicy ascii
	     String decodedMessage = "";
	     
	     
	     System.out.print("ZMIANA BLOKÓW ASCII NA CHAR\n");
	     
	     for(int i=0; i<blocks.length; i++){
	    	 int length = odszyfrowane[i].toString().length();
	    	 int rest_d = length%sizeOfAsciiReprezentation;
	    	 int numberOfSignsInBlock = length/sizeOfAsciiReprezentation;
	    	 if(rest_d>0) numberOfSignsInBlock++;
	    	 
	    	 String tmp = "";
	    	 
	    	 for(int j=0; j<numberOfSignsInBlock; j++){
	    		 
	    		 int safeStartIndex = length-j*sizeOfAsciiReprezentation-4;
	    		 if(safeStartIndex<0) safeStartIndex=0;
	    		 tmp=(char)Integer.parseInt(odszyfrowane[i].toString().substring(safeStartIndex, length-j*sizeOfAsciiReprezentation))+tmp;
	    	 }
	    	 
	    	 System.out.print(tmp+"\n");
	    	
	    	 decodedMessage+=tmp;
	    	 
	     }
	     
	     
	     System.out.print("ODSZYFROWANA WIADOMOŒÆ:\n");
	     System.out.print(decodedMessage+"\n");
	     
	     
	    }
	   public static  BigInteger NWD(BigInteger a, BigInteger b) {
	        if (b.compareTo(new BigInteger("0"))!=0){ return a; }
	        return NWD(b, (a.mod(b)));
	    }

}
