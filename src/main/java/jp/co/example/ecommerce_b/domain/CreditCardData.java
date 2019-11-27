package jp.co.example.ecommerce_b.domain;

/**
 * クレジットカード情報をJSON形式で受け取るドメイン.
 * 
 * @author riho.ikeda
 *
 */
public class CreditCardData {
	
 private String status;
 private String message;
 private String error_code;
/**
 * @return the status
 */
public String getStatus() {
	return status;
}
/**
 * @param status the status to set
 */
public void setStatus(String status) {
	this.status = status;
}
/**
 * @return the message
 */
public String getMessage() {
	return message;
}
/**
 * @param message the message to set
 */
public void setMessage(String message) {
	this.message = message;
}
/**
 * @return the error_code
 */
public String getError_code() {
	return error_code;
}
/**
 * @param error_code the error_code to set
 */
public void setError_code(String error_code) {
	this.error_code = error_code;
}
@Override
public String toString() {
	return "CreditCardData [status=" + status + ", message=" + message + ", error_code=" + error_code + "]";
}
 
 
 
 
 
 
 
 
 

}
