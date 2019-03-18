/**
 *<html>
 *<body>
 *	<P> Copyright(c)  ● JasonInternational ◆ Jason <jasonandy@hotail.com></p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.skeleton.common.utils.bcrypt;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.SecureRandom;
import java.util.regex.Pattern;

/**
 * @ClassName：BCryptPasswordEncoder
 * @Description： <p> BCryptPasswordEncoder  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/18 - 17:20
 * @Modify By：
 * @ModifyTime： 2019/3/18
 * @Modify marker：
 * @version V1.0
*/
public class BCryptPasswordEncoder {

    private Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

    private final Log logger = LogFactory.getLog(getClass());

    private final int strength;

    private final SecureRandom random;

    public BCryptPasswordEncoder() {
        this(-1);
    }

    /**
     * @param strength the log rounds to use, between 4 and 31
     */
    public BCryptPasswordEncoder(int strength) {
        this(strength, null);
    }

    /**
     * @param strength the log rounds to use, between 4 and 31
     * @param random the secure random instance to use
     *
     */
    public BCryptPasswordEncoder(int strength, SecureRandom random) {
        if (strength != -1 && (strength < BCrypt.MIN_LOG_ROUNDS || strength > BCrypt.MAX_LOG_ROUNDS)) {
            throw new IllegalArgumentException("Bad strength");
        }
        this.strength = strength;
        this.random = random;
    }

    public String encode(CharSequence rawPassword) {
        String salt;
        if (strength > 0) {
            if (random != null) {
                salt = BCrypt.gensalt(strength, random);
            }
            else {
                salt = BCrypt.gensalt(strength);
            }
        }
        else {
            salt = BCrypt.gensalt();
        }
        return BCrypt.hashpw(rawPassword.toString(), salt);
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword == null || encodedPassword.length() == 0) {
            logger.warn("Empty encoded password");
            return false;
        }

        if (!BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
            logger.warn("Encoded password does not look like BCrypt");
            return false;
        }

        return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }
}