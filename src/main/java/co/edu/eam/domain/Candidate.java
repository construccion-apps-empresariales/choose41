package co.edu.eam.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.*;
import java.util.Base64;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.util.ResourceUtils;

@Data
@Slf4j
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
    private String description;

    // All new candidates start with 0 positive votes
    private int votesUp = 0;

    // All new candidates start with 0 negtive votes
    private int votesDown = 0;

    @Lob
    private String image;

    public void setImage(){
        try {
            this.image = proccessImage("src/main/resources/static/img/avatar.png");
        } catch (IOException ioe){
            log.error(ioe.getMessage());
        }
    }

    public void setImage(String imagePath){
        try {
            this.image = proccessImage(imagePath);
        } catch (IOException ioe){
            log.error(ioe.getMessage());
        }
    }

    private String proccessImage(String imgPath) throws IOException {
        // read image from file
        FileInputStream stream = new FileInputStream(imgPath);
        // get byte array from image stream
        int bufLength = 2048;
        byte[] buffer = new byte[2048];
        byte[] data;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int readLength;
        while ((readLength = stream.read(buffer, 0, bufLength)) != -1) {
            out.write(buffer, 0, readLength);
        }
        data = out.toByteArray();
        String imageString = Base64.getEncoder().withoutPadding().encodeToString(data);
        out.close();
        stream.close();
        return imageString;
    }
}