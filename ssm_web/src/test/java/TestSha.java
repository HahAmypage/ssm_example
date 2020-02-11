
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestSha {

    @Test
    public void testSha256(){
        Sha512Hash hash = new Sha512Hash("123");
        System.out.println(hash.toString());
        System.out.println(hash.toString().length());
    }

    @Test
    public void testBCry(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        //加密
        String encode = bCryptPasswordEncoder.encode("111111");
        String encode2 = bCryptPasswordEncoder.encode("123");
        String encode3 = bCryptPasswordEncoder.encode("123");

        System.out.println(encode);
        System.out.println(encode2);
        System.out.println(encode3);

        //解密
        System.out.println(bCryptPasswordEncoder.matches("123",encode));
        System.out.println(bCryptPasswordEncoder.matches("123",encode2));
        System.out.println(bCryptPasswordEncoder.matches("123",encode3));
    }
}
