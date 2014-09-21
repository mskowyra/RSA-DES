import java.util.Random;



//http://www.herongyang.com/crypto/des_implJava_3.html
public class DES {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//przygotowanie wiadomosci i klucza
		     
		     String message = "test_abc";
		     System.out.println("Tekst do zaszyfrowania: "+message);
		     byte[] bytes = message.getBytes();
		     StringBuilder binary = new StringBuilder();
		     
		     for (byte b : bytes)
		     {
		        int val = b;
		        for (int i = 0; i < 8; i++)
		        {
		           binary.append((val & 128) == 0 ? 0 : 1);
		           val <<= 1;
		        }
		        //binary.append(' ');
		        
		     }
		     //System.out.println(String.valueOf(binary.length()+1));
		     System.out.println("'" + message + "' do zero-jedynek: " + binary+"");
		  
		     
		     
		     int n=2;
		     Random generator = new Random();
		     String klucz="";
		     for(int i = 0; i < 64; i++ )
		     {
		     int randomIndex = generator.nextInt( n );
		     klucz+=String.valueOf(randomIndex);
				}
		     
		     System.out.println("KLUCZ: "+klucz+"\n");
		     
		     
		     // System.out.println("dlugosc klucza: "+klucz.length());
		     
		      int[] IP = {
		         58, 50, 42, 34, 26, 18, 10, 2,
		         60, 52, 44, 36, 28, 20, 12, 4,
		         62, 54, 46, 38, 30, 22, 14, 6,
		         64, 56, 48, 40, 32, 24, 16, 8,
		         57, 49, 41, 33, 25, 17,  9, 1,
		         59, 51, 43, 35, 27, 19, 11, 3,
		         61, 53, 45, 37, 29, 21, 13, 5,
		         63, 55, 47, 39, 31, 23, 15, 7
		      };
		    
		       int[] E = {
		          32,  1,  2,  3,  4,  5,
		           4,  5,  6,  7,  8,  9,
		           8,  9, 10, 11, 12, 13,
		          12, 13, 14, 15, 16, 17,
		          16, 17, 18, 19, 20, 21,
		          20, 21, 22, 23, 24, 25,
		          24, 25, 26, 27, 28, 29,
		          28, 29, 30, 31, 32,  1
		       };
		        int[] INVP = {
		    	      40, 8, 48, 16, 56, 24, 64, 32,
		    	      39, 7, 47, 15, 55, 23, 63, 31,
		    	      38, 6, 46, 14, 54, 22, 62, 30,
		    	      37, 5, 45, 13, 53, 21, 61, 29,
		    	      36, 4, 44, 12, 52, 20, 60, 28,
		    	      35, 3, 43, 11, 51, 19, 59, 27,
		    	      34, 2, 42, 10, 50, 18, 58, 26,
		    	      33, 1, 41,  9, 49, 17, 57, 25
		    	   };
		       int[] S = {
		    		   14,  4, 13,  1,  2, 15, 11,  8,  3, 10,  6, 12,  5,  9,  0,  7, // S1
		    		    0, 15,  7,  4, 14,  2, 13,  1, 10,  6, 12, 11,  9,  5,  3,  8,
		    		    4,  1, 14,  8, 13,  6,  2, 11, 15, 12,  9,  7,  3, 10,  5,  0,
		    		   15, 12,  8,  2,  4,  9,  1,  7,  5, 11,  3, 14, 10,  0,  6, 13,
		    		   15,  1,  8, 14,  6, 11,  3,  4,  9,  7,  2, 13, 12,  0,  5, 10, // S2
		    		    3, 13,  4,  7, 15,  2,  8, 14, 12,  0,  1, 10,  6,  9, 11,  5,
		    		    0, 14,  7, 11, 10,  4, 13,  1,  5,  8, 12,  6,  9,  3,  2, 15,
		    		   13,  8, 10,  1,  3, 15,  4,  2, 11,  6,  7, 12,  0,  5, 14,  9,
		    		   10,  0,  9, 14,  6,  3, 15,  5,  1, 13, 12,  7, 11,  4,  2,  8, // S3
		    		   13,  7,  0,  9,  3,  4,  6, 10,  2,  8,  5, 14, 12, 11, 15,  1,
		    		   13,  6,  4,  9,  8, 15,  3,  0, 11,  1,  2, 12,  5, 10, 14,  7,
		    		    1, 10, 13,  0,  6,  9,  8,  7,  4, 15, 14,  3, 11,  5,  2, 12,
		    		    7, 13, 14,  3,  0,  6,  9, 10,  1,  2,  8,  5, 11, 12,  4, 15, // S4
		    		   13,  8, 11,  5,  6, 15,  0,  3,  4,  7,  2, 12,  1, 10, 14,  9,
		    		   10,  6,  9,  0, 12, 11,  7, 13, 15,  1,  3, 14,  5,  2,  8,  4,
		    		    3, 15,  0,  6, 10,  1, 13,  8,  9,  4,  5, 11, 12,  7,  2, 14,
		    		    2, 12,  4,  1,  7, 10, 11,  6,  8,  5,  3, 15, 13,  0, 14,  9, // S5
		    		   14, 11,  2, 12,  4,  7, 13,  1,  5,  0, 15, 10,  3,  9,  8,  6,
		    		    4,  2,  1, 11, 10, 13,  7,  8, 15,  9, 12,  5,  6,  3,  0, 14,
		    		   11,  8, 12,  7,  1, 14,  2, 13,  6, 15,  0,  9, 10,  4,  5,  3,
		    		   12,  1, 10, 15,  9,  2,  6,  8,  0, 13,  3,  4, 14,  7,  5, 11, // S6
		    		   10, 15,  4,  2,  7, 12,  9,  5,  6,  1, 13, 14,  0, 11,  3,  8,
		    		    9, 14, 15,  5,  2,  8, 12,  3,  7,  0,  4, 10,  1, 13, 11,  6,
		    		    4,  3,  2, 12,  9,  5, 15, 10, 11, 14,  1,  7,  6,  0,  8, 13,
		    		    4, 11,  2, 14, 15,  0,  8, 13,  3, 12,  9,  7,  5, 10,  6,  1, // S7
		    		   13,  0, 11,  7,  4,  9,  1, 10, 14,  3,  5, 12,  2, 15,  8,  6,
		    		    1,  4, 11, 13, 12,  3,  7, 14, 10, 15,  6,  8,  0,  5,  9,  2,
		    		    6, 11, 13,  8,  1,  4, 10,  7,  9,  5,  0, 15, 14,  2,  3, 12,
		    		   13,  2,  8,  4,  6, 15, 11,  1, 10,  9,  3, 14,  5,  0, 12,  7, // S8
		    		    1, 15, 13,  8, 10,  3,  7,  4, 12,  5,  6, 11,  0, 14,  9,  2,
		    		    7, 11,  4,  1,  9, 12, 14,  2,  0,  6, 10, 13, 15,  3,  5,  8,
		    		    2,  1, 14,  7,  4, 10,  8, 13, 15, 12,  9,  0,  3,  5,  6, 11
		    		      };   

		        int[] P = {
		    	      16,  7, 20, 21,
		    	      29, 12, 28, 17,
		    	       1, 15, 23, 26,
		    	       5, 18, 31, 10,
		    	       2,  8, 24, 14,
		    	      32, 27,  3,  9,
		    	      19, 13, 30,  6,
		    	      22, 11,  4, 25
		    	   };
		     // - do permutacji klucza
		      
		      
		       int[] PC1 = {
		          57, 49, 41, 33, 25, 17,  9,
		           1, 58, 50, 42, 34, 26, 18,
		          10,  2, 59, 51, 43, 35, 27,
		          19, 11,  3, 60, 52, 44, 36,
		          63, 55, 47, 39, 31, 23, 15,
		           7, 62, 54, 46, 38, 30, 22,
		          14,  6, 61, 53, 45, 37, 29,
		          21, 13,  5, 28, 20, 12,  4
		       };
		        int[] PC2 = {
		          14, 17, 11, 24,  1,  5,
		           3, 28, 15,  6, 21, 10,
		          23, 19, 12,  4, 26,  8,
		          16,  7, 27, 20, 13,  2,
		          41, 52, 31, 37, 47, 55,
		          30, 40, 51, 45, 33, 48,
		          44, 49, 39, 56, 34, 53,
		          46, 42, 50, 36, 29, 32
		       };
		       
		       int[] SHIFTS = {
		            1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
		         };
		        
		    
		      
		      // dla wiadomosci
		        char[] cArray = binary.toString().toCharArray();
		        char[] cArray_2=new char[64];
		        wypelnienie(cArray_2, '0');	      
		      
		      cArray_2=permutacja(IP,cArray,64);
		     // System.out.println("Pierwsza permutacja wiadomosci: "+tchar2str(cArray_2));
		      
		      
		      
	  // dla klucza
		      
		      	char[] cArray_k = klucz.toCharArray();
		        char[] cArray_k2=new char[64];
		              
		        cArray_k2=permutacja2(IP, cArray_k, 56);  
		        
		       		        
		       char[] lb_klucz=new char[28];
		       char[] pb_klucz=new char[28];
		       
		    
		       for(int i=0;i<28;i++)
		    	   lb_klucz[i]=cArray_k2[i];
		       //System.out.println("lewa strona klucza:"+tchar2str(lb_klucz));
		       
		       for(int i=28;i<56;i++)
		    	   pb_klucz[i-28]=cArray_k2[i];
		      // System.out.println("prawa strona klucza:"+tchar2str(pb_klucz));
		       
		       char[] lb_wiadomosc=new char[32];
		       char[] pb_wiadomosc=new char[32];
		       char[]temp_pb_wiadomosc=new char[32];
		       char[]roz=new char[48];
		       char[]PR=new char[32];
		       
		       for(int i=0;i<32;i++)
		    	   lb_wiadomosc[i]=cArray_2[i];
		       
		       for(int i=32;i<64;i++)
		    	   pb_wiadomosc[i-32]=cArray_2[i];
		       
		       
		       
		       String[][] tab=new String[2][1];
		       char[] calosc=new char[56];
		       char[] perm2=new char[48];
	        
	       //1 interacja 
	       // int j=2;//numer interacji
	        //klucz
	        String[] klucze=new String[16];
	        for(int i=0;i<16;i++)
	        {
	        tab=przesuniecie(i, SHIFTS, lb_klucz, pb_klucz);
	        lb_klucz=tab[0][0].toCharArray();
	        pb_klucz=tab[1][0].toCharArray();
	        //System.out.println("\tNOWA lewa strona klucza:  "+tab[0][0]); 
	        //System.out.println("\tNOWA prawa strona klucza: "+tab[1][0]);
	        
	        calosc=polacz(tab);
	        //System.out.println("\tCa³oœæ klucza:"+tchar2str(calosc));
	        		        
	        perm2=permutacja2(PC2, calosc, 48);
	        klucze[i]=tchar2str(perm2);
	       // System.out.println("\tDruga permutacja klucza: "+tchar2str(perm2));
	       //wiadomosc
	        temp_pb_wiadomosc=pb_wiadomosc;
	        roz=rozszerzenie(pb_wiadomosc, E);
	        roz=xor(roz,perm2);
	        PR=sblok(S,roz);
	        PR=permutacja2(P,PR,32);
	        pb_wiadomosc=xor(PR,lb_wiadomosc);
	        lb_wiadomosc=temp_pb_wiadomosc;
	        }
	        String zamiana=tchar2str(pb_wiadomosc)+tchar2str(lb_wiadomosc);
	        char[] koniec=new char[64];
	        koniec=permutacja2(INVP, zamiana.toCharArray(), 64);
	        System.out.println("Wiadomosc zaszyfrowana: "+tchar2str(koniec));
	        
	        
	        //odszyfrowanie
	       
		       
		       
		       
	        char[] cArray_dec = koniec;
	        char[] cArray_dec2=new char[64];
	        //wypelnienie(cArray_dec2, '0');	      
	      
	        cArray_dec2=permutacja(IP,cArray_dec,64);
	        
	        for(int i=0;i<32;i++)
		    	   lb_wiadomosc[i]=cArray_dec2[i];
		       
		       for(int i=32;i<64;i++)
		    	   pb_wiadomosc[i-32]=cArray_dec2[i];
	        for(int i=0;i<16;i++)
	        {
	       
	       //wiadomosc
	        temp_pb_wiadomosc=pb_wiadomosc;
	        roz=rozszerzenie(pb_wiadomosc, E);
	        roz=xor(roz,klucze[15-i].toCharArray());
	        PR=sblok(S,roz);
	        PR=permutacja2(P,PR,32);
	        pb_wiadomosc=xor(PR,lb_wiadomosc);
	        lb_wiadomosc=temp_pb_wiadomosc;
	        }
	        zamiana="";
	        	
	        zamiana=tchar2str(pb_wiadomosc)+tchar2str(lb_wiadomosc);
	        char[] koniec2=new char[64];
	        koniec2=permutacja2(INVP, zamiana.toCharArray(), 64);
	        //System.out.println("odszyfrowana: "+tchar2str(koniec2));
	        
	        String st = new String(koniec2);
	        

	        /*
	        String number = "01110100"; 
	        byte numberByte = (byte) Integer.parseInt(number, 2); 
	        System.out.println(numberByte);
	        int i = numberByte;
	        String aChar = new Character((char)i).toString();
	        System.out.println(aChar);*/
	        
	        //System.out.println("KONIEC: "+decr(cArray)+"");
	        System.out.println("KONIEC: "+decr(koniec2)+"");
	}
	
static String decr(char[] in)
{String out="";
String[] blok=new String[8]; 

int j=0,k=0;
blok[0]=new String("");
for(int i=0;i<in.length;i++)
{
	
	if(j<8)
		j++;
	else {
		j=1;
		k++;
		blok[k]=new String("");
	}
	blok[k]+=in[i];
	
}
int w=0;
String aChar;
for(int i=0;i<8;i++)
{
byte numberByte = (byte) Integer.parseInt(blok[i], 2); //so mode 2
w = numberByte;
aChar = new Character((char)w).toString();
out+=aChar;
}
//System.out.println(blok[0]);
return out;
}
	
static char[] sblok(int[] S,char[] in)
{	char[] out=new char[32];
	String[] blok=new String[8]; 
	// podzial na bloki 6 bitowe
	//String t="110110111010011001100000010010011111010010100110";
	//in=t.toCharArray();
	int j=0,k=0;
	blok[0]=new String("");
	for(int i=0;i<in.length;i++)
	{
		
		if(j<6)
			j++;
		else {
			j=1;
			k++;
			blok[k]=new String("");
		}
		blok[k]+=in[i];
		
	}
	//char[] temp=blok[0].charAt();
	//char[] temp=blok[0].toCharArray();
	char a,b,c,d,e,f;
	int rzad=0,kolumna=0,nblok=0,rozmiar=0;
	String by="",temp="",calosc="";
	
	for(int i=0;i<8;i++)
	{
	 a=blok[i].charAt(0);
	 b=blok[i].charAt(5);
	
	String n=Character.toString(a)+Character.toString(b);
	rzad=Integer.parseInt(n, 2);
	
	 c=blok[i].charAt(1);
	 d=blok[i].charAt(2);
	 e=blok[i].charAt(3);
	 f=blok[i].charAt(4);
	String m=Character.toString(c)+Character.toString(d)+Character.toString(e)+Character.toString(f);
	kolumna=Integer.parseInt(m, 2);
	//int aa=Integer.parseInt(n);
	
	nblok=64*i+rzad*16+kolumna;
	by = Integer.toBinaryString(S[nblok]);
	rozmiar=by.length();
	for(int z=0;z<4-rozmiar;z++)
		temp+="0";
	
	calosc+=temp+by;
	by="";
	temp="";
	}
	//System.out.println("\tSBLOK: "+blok[7]+" rzad "+rzad+" kolumna "+kolumna+" = "+S[nblok] +" temp "+calosc);
	//System.out.println("\tint: "+Integer.toString(a));
	out=calosc.toCharArray();
	return out;
}
static char[] xor(char[] tab1, char[] tab2)
{	char[] out=new char[tab1.length];
	int a,b,w;
	String temp="";
	for(int i=0;i<tab1.length;i++)
	{
		a=tab1[i];
		b=tab2[i];
		w=a^b;
		temp+=Integer.toString(w);
	}
	out=temp.toCharArray();
	return out;
}
static char[] rozszerzenie(char[] pb_wiadomosc,int[] E)
{	char[] tab=new char[48];
	
	for(int i=0;i<48;i++)
	{
	  int zm=E[i];
	  
	  tab[i]=pb_wiadomosc[zm-1]; 
	  
	}
	
	return tab;
}
static char[] permutacja(int[] tabp, char[] in, int rozm)
{
	char[] out=new char[rozm];

	for(int i=0;i<in.length;i++)
    {
  	  int zm=tabp[i];
  	  
  	  out[i]=in[zm-1]; 
  	  
    }
		
	return out;
}

static char[] permutacja2(int[] tabp, char[] in, int rozm)
{
	char[] out=new char[rozm];

	for(int i=0;i<rozm;i++)
    {
  	  int zm=tabp[i];
  	  
  	  out[i]=in[zm-1]; 
  	  
    }
		
	return out;
}

static String tchar2str(char[] in)
{ 
	String out="";
	for (char c : in)
       out+=c;
    
	return out;
}

static char[] wypelnienie(char[] in,char znak)
{
	for(int i=0;i<in.length;i++)
    	in[i]=znak;
    
	
	return in;
}

static String[][] przesuniecie(int interacja,int[] SHIFTS,char[] lb,char[] pb)
{	String[][] tab=new String[2][1];
	
char last[]={lb[0],lb[1],pb[0],pb[1]};
for(int i=0;i<lb.length;i++)
{
	  
	   int przes=SHIFTS[interacja];
	   
	   if(przes==1){
		   
		   if(i+1>=lb.length)
		   {
			   lb[i]=last[0];
 		   pb[i]=last[2];
 		   
		   } else
		   {
			   lb[i]=lb[i+1];
 		   pb[i]=pb[i+1];  
		   }
	   }else
	   {	if(i+1>=lb.length)
	   		{
		   		lb[i]=last[0];
		   		pb[i]=last[2];
		   	
	   		} else if(i+2>=lb.length)
	   		{
	   			lb[i]=last[1];
		   		pb[i]=last[3];
	   		}else
	   		{
		   lb[i]=lb[i+2];
		   pb[i]=pb[i+2];
	   		}
	   
	   }
}

	tab[0][0]=tchar2str(lb);
	tab[1][0]=tchar2str(pb);
	return tab;
}

static char[] polacz(String[][] tab)
{	char[] calosc=new char[56];
	String a=tab[0][0]+tab[1][0];
	calosc=a.toCharArray();
	return calosc;
}

}
