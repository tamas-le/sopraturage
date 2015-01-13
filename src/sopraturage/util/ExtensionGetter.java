package sopraturage.util;

public class ExtensionGetter {
	
	public static String getExtension(String arg){
		String aux;
		for (int i=0;i<arg.length();i++){
		
			aux=arg.substring(i);
			if (aux.startsWith(".")){
				return aux;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		String salut="salut.jpg";
		System.out.println(salut);
		salut=getExtension(salut);
		System.out.println(salut);
	}

}
