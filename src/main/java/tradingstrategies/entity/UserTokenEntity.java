package tradingstrategies.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("user_token")
public class UserTokenEntity {

    private Integer id;

    private String user;

    private String token;
}
