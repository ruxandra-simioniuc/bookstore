package pos.proiect.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.proiect.bookstore.model.jwt.JwtBlacklist;

public interface JwtBlacklistRepository extends JpaRepository<JwtBlacklist, String> {
}
