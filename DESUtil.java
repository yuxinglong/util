

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 3DES加密工具
 * @author yanglb
 *
 */
public class DESUtil {
	private static final String Algorithm = "xxx";
	
	private static final Log LOG = LogFactory.getLog(DESUtil.class);
	
	private static final String KEY_STRING = "xxx";
	
	private static final SecretKey DEFAULT_KEY = new SecretKeySpec(KEY_STRING.getBytes(), Algorithm);
	
	private DESUtil(){
		
	}

	/**
	 * 使用默认的密钥将明文串生成相应的密文数组
	 * @param plainText
	 * @return
	 */
	public static byte[] encrypt(String plainText){
		return encrypt(plainText, DEFAULT_KEY);	
	}
	
	/**
	 * 使用默认的密钥将明文数组生成相应的密文数组
	 * @param plainBytes
	 * @return
	 */
	public static byte[] encrypt(byte[] plainBytes){
		return encrypt(plainBytes,DEFAULT_KEY);
	}
	
	/**
	 * 根据指定的密钥和明文串生成密文数组
	 * @param plainText
	 * @param key
	 * @return
	 */
	public static byte[] encrypt(String plainText,SecretKey key){
		if(isBlankString(plainText)){
			LOG.error("待加密数据为空：" + plainText);
			return null;
		}else{
			return encrypt(plainText.getBytes(), key);
		}
	}
	
	/**
	 * 根据指定的密钥和明文数组生成密文数组
	 * @param plainBytes
	 * @param key
	 * @return
	 */
	public static byte[] encrypt(byte[] plainBytes,SecretKey key){
		if(isBlankBytes(plainBytes)){
			LOG.error("待加密数据为空：" + plainBytes);
			return null;
		}
		if(key == null){
			LOG.error("密钥为空");
			return null;
		}
		try {
			Cipher cipher = Cipher.getInstance(Algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher.doFinal(plainBytes);
		} catch (NoSuchAlgorithmException e) {
			LOG.error("不存在的加密算法", e);
		} catch (NoSuchPaddingException e) {
			LOG.error("缺少必须的参数", e);
		} catch (InvalidKeyException e) {
			LOG.error("无效的密钥", e);
		} catch (IllegalBlockSizeException e) {
			LOG.error("加密的数据块大小有误", e);
		} catch (BadPaddingException e) {
			LOG.error("待加密数据有误", e);
		}
		return null;
	}
	
	/**
	 * 使用默认的密钥将密文串生成相应的明文数组
	 * @param plainText
	 * @return
	 */
	public static byte[] decrypt(String cipherText){
		return decrypt(cipherText, DEFAULT_KEY);	
	}
	
	/**
	 * 使用默认的密钥将密文数组生成相应的明文数组
	 * @param plainBytes
	 * @return
	 */
	public static byte[] decrypt(byte[] cipherBytes){
		return decrypt(cipherBytes,DEFAULT_KEY);
	}
	
	/**
	 * 根据指定的密钥和密文串生成明文数组
	 * @param plainText
	 * @param key
	 * @return
	 */
	public static byte[] decrypt(String cipherText,SecretKey key){
		if(isBlankString(cipherText)){
			LOG.error("待解密数据为空：" + cipherText);
			return null;
		}else{
			return decrypt(cipherText.getBytes(), key);
		}
	}
	
	/**
	 * 根据指定的密钥和密文数组生成明文数组
	 * @param plainBytes
	 * @param key
	 * @return
	 */
	public static byte[] decrypt(byte[] cipherBytes,SecretKey key){
		if(isBlankBytes(cipherBytes)){
			LOG.error("待解密数据为空：" + cipherBytes);
			return null;
		}
		if(key == null){
			LOG.error("密钥为空");
			return null;
		}
		try {
			Cipher cipher = Cipher.getInstance(Algorithm);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return cipher.doFinal(cipherBytes);
		} catch (NoSuchAlgorithmException e) {
			LOG.error("不存在的加密算法", e);
		} catch (NoSuchPaddingException e) {
			LOG.error("缺少必须的参数", e);
		} catch (InvalidKeyException e) {
			LOG.error("无效的密钥", e);
		} catch (IllegalBlockSizeException e) {
			LOG.error("解密的数据块大小有误", e);
		} catch (BadPaddingException e) {
			LOG.error("待解密数据有误", e);
		}
		return null;
	}
	
	/**
	 * 根据指定的密钥串生成3DES对应的密钥
	 * @param keyString
	 * @return
	 */
	public static SecretKey  generateKey(String keyString){
		if(!isBlankString(keyString)){
			return generateKey(keyString.getBytes());
		}else{
			LOG.error("生产密钥失败");
			return null;
		}
	}
	
	/**
	 * 根据指定的密钥字节数组生成3DES对应的密钥
	 * @param keyByte
	 * @return
	 */
	public static SecretKey  generateKey(byte[] keyByte){
		if(!isBlankBytes(keyByte)){
			return new SecretKeySpec(keyByte, Algorithm);
		}else{
			LOG.error("生产密钥失败");
			return null;
		}
	}
	
	/**
	 * 用默认密钥加密字符串并用BASE64编码
	 * @param plainString
	 * @return
	 */
	public static String encryptAndBaseEncode(String plainString){
		return encryptAndBaseEncode(plainString,DEFAULT_KEY);
	}
	
	/**
	 * 用默认密钥加密字符数组并用BASE64编码
	 * @param plainBytes
	 * @return
	 */
	public static String encryptAndBaseEncode(byte[] plainBytes){
		return encryptAndBaseEncode(plainBytes,DEFAULT_KEY);
	}
	
	/**
	 * 用指定密钥加密字符串并用BASE64编码
	 * @param plainString
	 * @param key
	 * @return
	 */
	public static String encryptAndBaseEncode(String plainString,SecretKey key){
		if(!isBlankString(plainString)){
			return encryptAndBaseEncode(plainString.getBytes(),key);
		}else{
			LOG.error("待加密数据为空：" + plainString);
			return null;
		}
	}
	
	/**
	 * 用指定密钥加密字符数组并用BASE64编码
	 * @param plainBytes
	 * @param key
	 * @return
	 */
	public static String encryptAndBaseEncode(byte[] plainBytes,SecretKey key){
		Base64 base = new Base64();
		byte[] bytes = encrypt(plainBytes, key);
		if(!isBlankBytes(bytes)){
			return base.encodeAsString(bytes);
		}else{
			return null;
		}
	}
	
	/**
	 * 用BASE64解码字符串，然后用默认密钥解密
	 * @param cipherString
	 * @return
	 */
	public static String decryptAndBaseDecode(String cipherString){
		return decryptAndBaseDecode(cipherString,DEFAULT_KEY);
	}
	
	/**
	 * 用BASE64解码字符串，然后用指定密钥解密
	 * @param cipherString
	 * @param key
	 * @return
	 */
	public static String decryptAndBaseDecode(String cipherString,SecretKey key){
		if(!isBlankString(cipherString)){
			Base64 base = new Base64();
			return new String(decrypt(base.decode(cipherString)));
		}else{
			LOG.error("待解密数据为空：" + cipherString);
			return null;
		}
	}	
	
	/**
	 * 判断字节数组是否为空
	 * @param bytes
	 * @return
	 */
	private static boolean isBlankBytes(byte[] bytes){
		if(bytes != null && bytes.length > 0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 判断字符串是否为空
	 * @param string
	 * @return
	 */
	private static boolean isBlankString(String string){
		if(string != null && string.length() > 0){
			return false;
		}else{
			return true;
		}
	}
}
