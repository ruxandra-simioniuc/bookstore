package pos.proiect.bookstore.service.interfaces;

import pos.proiect.bookstore.model.jwt.JwtBlacklist;

import java.util.List;

public interface JwtBlacklistService {

    Boolean isJWTinBlacklist(String jwt);
    Boolean addJWTtoBlacklist(String jwt);
    List<JwtBlacklist> getAllJWTs();
    Boolean isExpired(String jwt);
}
