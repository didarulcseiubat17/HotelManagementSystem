package license;



import java.security.NoSuchAlgorithmException;


public class Customer {

	private String code;
	private String code_param;
	

// Constructor accepts code parameter during instantiation of the class 
	public Customer(String code_param){
		
		this.code_param = code_param;
	}
// returns the paramater being used for generating Customercode	
	public String getCodeParam(){
		
		
		return this.code_param;
	}
// sets the parameter to be used for creating  customer code
	public void setCodeParam(String code_param){
		
	this.code_param = code_param;
	}
	
// returns the 8 character  customer code 
	
	public String getCode(){
		
		return this.code;
	}
	
// returns the  12 character customer code generated by  using the parameter supplied to it 
	
	
	public String GenerateCode() throws NoSuchAlgorithmException{
		
		
	String	codeEncoded = calculateSecurityHash(this.code_param,"MD2") +  calculateSecurityHash(this.code_param,"MD5") +
	calculateSecurityHash(this.code_param,"SHA1");	
	codeEncoded =codeEncoded.replaceAll("[^a-zA]", "");
	int index = 0;
	code="";
	for(int i=0;i<=7;i++){
	index = (int)Math.floor(Math.random()*codeEncoded.length());	
	code = code+ codeEncoded.charAt(index);	
	}
    code = code.toUpperCase();
    return this.code;
    
    
	}
	
// calculate the security hash for the string provided apply the specified algorithm	
	private String calculateSecurityHash(String stringInput, String algorithmName)
			 throws java.security.NoSuchAlgorithmException {
			 String hexMessageEncode = "";
			 byte[] buffer = stringInput.getBytes();
			 java.security.MessageDigest messageDigest =
			 java.security.MessageDigest.getInstance(algorithmName);
			 messageDigest.update(buffer);
			 byte[] messageDigestBytes = messageDigest.digest();
			 for (int index=0; index < messageDigestBytes.length ; index ++) {
			  int countEncode = messageDigestBytes[index] & 0xff;
			  if (Integer.toHexString(countEncode).length() == 1) hexMessageEncode = hexMessageEncode + "0";
			  hexMessageEncode = hexMessageEncode + Integer.toHexString(countEncode);
			 }
			 return hexMessageEncode;
			}
	
//	public static void main(String[] args){
//	
//		String customerCode;
//		//Use case of this class
//	 // provide the code parameter through constructor
//	// invoke GenerateCode method which will return the customer code
//	// the generated Customer code will be in turn used for generating SerialKey at SerialKeyGenerator
//		Customer customer = new Customer("Dreamsys Restaurant");
//		try {
//	 		customerCode =customer.GenerateCode();
//	 		System.out.println(customerCode);
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
	

