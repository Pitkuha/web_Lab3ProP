package Und;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RESULTS")
@SecondaryTable(name="RESULTSTWO")
public class Result implements Serializable {
    private static final long serialVersionUID = -5170875020617735653L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="X")
    private double X;
    @Column(name="Y")
    private double Y;
    @Column(name="R", table ="RESULTSTWO")
    private double R;
    @Column(name="ENTERING", table="RESULTSTWO")
    private boolean ENTERING;
    @Column(name="SESSIONID")
    private String SEESIONID;

    //TODO ВОТ ЭТО ГОВНО
//    @OneToMany(
//            mappedBy = "RESULTS"
//    )
//    List<ResultTwo> rt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }

    public String getSEESIONID() {
        return SEESIONID;
    }

    public void setSEESIONID(String SEESIONID) {
        this.SEESIONID = SEESIONID;
    }

    public boolean getENTERING() {
        return ENTERING;
    }

    public void setENTERING(boolean ENTERING) {
        this.ENTERING = ENTERING;
    }

    public static String drawPointJS(float x, float y, String col){
        return "drawPoint("+Float.toString(x) + ", " + Float.toString(y) + ", "+col+");";
    }
    public static String drawPointJSD(double x, double y, String col){
        return "drawPoint("+Double.toString(x) + ", " + Double.toString(y) + ", "+col+");";
    }
}
