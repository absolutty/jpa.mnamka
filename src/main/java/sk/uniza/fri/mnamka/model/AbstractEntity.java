package sk.uniza.fri.mnamka.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sk.uniza.fri.mnamka.helper.FieldValidator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity implements FieldValidator {

    public static final long NEW_ENTITY_ID_INDICATOR = -1;

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY) private Long id;

    public AbstractEntity(boolean isNewEntityBeingCreated) {
        if (isNewEntityBeingCreated) {
            this.id = NEW_ENTITY_ID_INDICATOR;
        }
    }

    public boolean isNewEntityBeingCreated() {
        return (this.id == NEW_ENTITY_ID_INDICATOR);
    }


    @Override
    public boolean anyRequiredFieldIsEmpty() {
        throw new UnsupportedOperationException("[anyRequiredFieldIsEmpty()]: Method not implemented yet!");
    }

    @Override
    public boolean anyNumberFieldIsNotCorrect() {
        throw new UnsupportedOperationException("[anyNumberFieldIsNotCorrect()]: Method not implemented yet!");
    }

}
