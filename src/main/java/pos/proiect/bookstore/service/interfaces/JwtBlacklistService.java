package pos.proiect.bookstore.service.interfaces;

import pos.proiect.bookstore.model.jwt.JwtBlacklist;

import java.util.List;

public interface JwtBlacklistService {

    boolean isJWTinBlacklist(String jwt);
    boolean addJWTtoBlacklist(String jwt);
    List<JwtBlacklist> getAllJWTs();
    boolean isExpired(String jwt);
}
