import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class TestMD5 {

    //加密
    @Test
    public void testMDS(){
        Md5Hash hash = new Md5Hash("123");
        System.out.println(hash);
    }

    //加盐 加密
    @Test
    public void testMD5salt(){
        String salt = "111";
        Md5Hash hash = new Md5Hash("123",salt);
        System.out.println(hash);
    }

    //加随机盐，加密
    @Test
    public void testMD5randomSalt(){
        //随机盐要存储
        SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
        String salt = secureRandomNumberGenerator.nextBytes().toHex();

        Md5Hash hash = new Md5Hash("123",salt);
        System.out.println(hash);
    }

    //加随机盐，迭代次数，加密
    @Test
    public void testMD5randomSaleTimes(){
        SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
        String salt = secureRandomNumberGenerator.nextBytes().toHex();
        Md5Hash hash = new Md5Hash("123",salt,300000);
        System.out.println(hash);
    }
}
