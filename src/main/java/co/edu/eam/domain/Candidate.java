package co.edu.eam.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Data
@Entity
@Table(name = "candidates")
public class Candidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El campo no puede estar vacio")
    @Size(min = 2, max = 30, message = "Debe ingresar minimo 2 letras y maximo 30")
    private String title;

    @NotNull(message = "El campo no puede estar vacio")
    @Size(min = 10, max = 250, message = "Debe ingresar una descripción más larga de maximo 250 caracteres")
    private int description;

    // All new candidates start with 0 positive votes
    private int votesUp = 0;

    // All new candidates start with 0 negtive votes
    private int votesDown = 0;

    // When save a new candidate, de default img will be an avatar if no image selected
    private String image = "default data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCA0NDggNTEyIj48IS0tISBGb250IEF3ZXNvbWUgUHJvIDYuMi4wIGJ5IEBmb250YXdlc29tZSAtIGh0dHBzOi8vZm9udGF3ZXNvbWUuY29tIExpY2Vuc2UgLSBodHRwczovL2ZvbnRhd2Vzb21lLmNvbS9saWNlbnNlIChDb21tZXJjaWFsIExpY2Vuc2UpIENvcHlyaWdodCAyMDIyIEZvbnRpY29ucywgSW5jLiAtLT48cGF0aCBkPSJNMzcwLjcgOTYuMUMzNDYuMSAzOS41IDI4OS43IDAgMjI0IDBTMTAxLjkgMzkuNSA3Ny4zIDk2LjFDNjAuOSA5Ny41IDQ4IDExMS4yIDQ4IDEyOHY2NGMwIDE2LjggMTIuOSAzMC41IDI5LjMgMzEuOUMxMDEuOSAyODAuNSAxNTguMyAzMjAgMjI0IDMyMHMxMjIuMS0zOS41IDE0Ni43LTk2LjFjMTYuNC0xLjQgMjkuMy0xNS4xIDI5LjMtMzEuOVYxMjhjMC0xNi44LTEyLjktMzAuNS0yOS4zLTMxLjl6TTMzNiAxNDR2MTZjMCA1My00MyA5Ni05NiA5NkgyMDhjLTUzIDAtOTYtNDMtOTYtOTZWMTQ0YzAtMjYuNSAyMS41LTQ4IDQ4LTQ4SDI4OGMyNi41IDAgNDggMjEuNSA0OCA0OHpNMTg5LjMgMTYyLjdsLTYtMjEuMmMtLjktMy4zLTMuOS01LjUtNy4zLTUuNXMtNi40IDIuMi03LjMgNS41bC02IDIxLjItMjEuMiA2Yy0zLjMgLjktNS41IDMuOS01LjUgNy4zczIuMiA2LjQgNS41IDcuM2wyMS4yIDYgNiAyMS4yYy45IDMuMyAzLjkgNS41IDcuMyA1LjVzNi40LTIuMiA3LjMtNS41bDYtMjEuMiAyMS4yLTZjMy4zLS45IDUuNS0zLjkgNS41LTcuM3MtMi4yLTYuNC01LjUtNy4zbC0yMS4yLTZ6TTExMi43IDMxNi41QzQ2LjcgMzQyLjYgMCA0MDcgMCA0ODIuM0MwIDQ5OC43IDEzLjMgNTEyIDI5LjcgNTEySDEyOFY0NDhjMC0xNy43IDE0LjMtMzIgMzItMzJIMjg4YzE3LjcgMCAzMiAxNC4zIDMyIDMydjY0bDk4LjMgMGMxNi40IDAgMjkuNy0xMy4zIDI5LjctMjkuN2MwLTc1LjMtNDYuNy0xMzkuNy0xMTIuNy0xNjUuOEMzMDMuOSAzMzguOCAyNjUuNSAzNTIgMjI0IDM1MnMtNzkuOS0xMy4yLTExMS4zLTM1LjV6TTE3NiA0NDhjLTguOCAwLTE2IDcuMi0xNiAxNnY0OGgzMlY0NjRjMC04LjgtNy4yLTE2LTE2LTE2em05NiAzMmM4LjggMCAxNi03LjIgMTYtMTZzLTcuMi0xNi0xNi0xNnMtMTYgNy4yLTE2IDE2czcuMiAxNiAxNiAxNnoiLz48L3N2Zz4=";

}