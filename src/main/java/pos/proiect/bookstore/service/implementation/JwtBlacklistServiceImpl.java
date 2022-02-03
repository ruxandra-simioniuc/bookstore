package pos.proiect.bookstore.service.implementation;

import org.springframework.stereotype.Service;
import pos.proiect.bookstore.model.jwt.JwtBlacklist;
import pos.proiect.bookstore.repository.JwtBlacklistRepository;
import pos.proiect.bookstore.service.interfaces.JwtBlacklistService;

import java.util.List;

@Service
public class JwtBlacklistServiceImpl implements JwtBlacklistService {

    private JwtBlacklistRepository jwtBlacklistRepository;

    public JwtBlacklistServiceImpl(JwtBlacklistRepository jwtBlacklistRepository) {
        this.jwtBlacklistRepository = jwtBlacklistRepository;
    }

    @Override
    public Boolean isJWTinBlacklist(String jwt) {
        return jwtBlacklistRepository.findById(jwt).isPresent();

    }

    @Override
    public Boolean addJWTtoBlacklist(String jwt) {
        JwtBlacklist jwtBlacklist = new JwtBlacklist();
        jwtBlacklist.setJwt(jwt);
        try{
            jwtBlacklistRepository.save(jwtBlacklist);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<JwtBlacklist> getAllJWTs() {
        return jwtBlacklistRepository.findAll();
    }
}
