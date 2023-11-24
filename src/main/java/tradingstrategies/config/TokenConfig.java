package tradingstrategies.config;

import com.jcy.tradingstrategies.dao.UserTokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenConfig implements CommandLineRunner {

    public static final ConcurrentHashMap<String, String> USER_TOKEN = new ConcurrentHashMap<>();

    @Autowired
    private UserTokenDao userTokenDao;


    @Override
    public void run(String... args) throws Exception {
//        List<UserTokenEntity> userTokenEntityList = userTokenDao.selectList(null);
//        for (UserTokenEntity userTokenEntity : userTokenEntityList) {
//            USER_TOKEN.put(userTokenEntity.getUser(), userTokenEntity.getToken());
//        }
    }
}

























