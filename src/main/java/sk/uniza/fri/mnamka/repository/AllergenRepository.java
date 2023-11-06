package sk.uniza.fri.mnamka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.uniza.fri.mnamka.model.AllergenModel;

public interface AllergenRepository extends JpaRepository<AllergenModel, Integer> {

}
