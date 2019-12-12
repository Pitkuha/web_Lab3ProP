package beans;

import Und.EntityManagerUtil;
import Und.ParamsManager;
import Und.Result;

import org.primefaces.PrimeFaces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static Und.Result.drawPointJS;
import static Und.Result.drawPointJSD;
import static Und.ParamsManager.round;

@SessionScoped
@ManagedBean(name="result")
public class ResultBean {
    private EntityManager entityManager = EntityManagerUtil.getEntityManager();
    private final String sessionID;
    //
    private double outsr;
    //
    public ResultBean(){
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(true);
        sessionID = session.getId();

    }

    public void checkClick(){
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        String sx = requestParameterMap.get("pX").replace(',','.');
        String sy = requestParameterMap.get("pY").replace(',','.');
        String sr = requestParameterMap.get("pR").replace(',','.');

        float x= Float.parseFloat(sx);
        float y= Float.parseFloat(sy);
        float r= Float.parseFloat(sr);

        Result result = new Result();
        try{
            entityManager.getTransaction().begin();

            result.setSEESIONID(sessionID);
            result.setX(x);
            result.setY(y);
            result.setR(r);
            result.setENTERING(ParamsManager.isInArea(x,y,r));

            result = entityManager.merge(result);

            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }

        String col;
        if (ParamsManager.isInArea(x,y,r)){
            col = "'#00FF00'";
        } else {
            col = "'#FF0000'";
        }

        PrimeFaces.current().executeScript(drawPointJS(x, y, col));
    }

    public void callRedraw(){
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        String sr = requestParameterMap.get("pR").replace(',','.');
        double ssr= Double.parseDouble(sr);
        //
        outsr = ssr;
        //
        List<Result> res = getAllResults();
        for (int i = 0; i < res.size(); i++) {
            Result r = res.get(i);
            String col;
            if (ParamsManager.isInArea(r.getX(),r.getY(),ssr)){
                col = "'#00FF00'";
            } else {
                col = "'#FF0000'";
            }
            PrimeFaces.current().executeScript(drawPointJSD(r.getX(), r.getY(), col));
        }
    }

    public void removeResult(){
            Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String sid = requestParameterMap.get("removeForm:param-id");
            long id = Long.parseLong(sid);
            System.out.println(id + " " + "fdddddddddfdfdfdfdfd");

            try{
                entityManager.getTransaction().begin();
                entityManager.createQuery("delete from Result e where e.id = :id").setParameter("id", id).executeUpdate();
                entityManager.getTransaction().commit();
                //entityManager.createQuery("delete from Result e where e.SEESIONID = :id").setParameter("id",sessionID);
                //entityManager.createQuery("delete from Result e where e.id = :id").setParameter("id",id);
            }catch (Exception e){
                entityManager.getTransaction().rollback();
            }
    }

    public List<Result> getAllResults(){
            entityManager.getTransaction().begin();
            @SuppressWarnings("unchecked")
            List<Result> Results = entityManager.createQuery("select e from Result e where e.SEESIONID = :id").setParameter("id",sessionID).getResultList();
            entityManager.getTransaction().commit();
        for (int i = 0; i < Results.size(); i++) {
            Result r = Results.get(i);
            r.setX(round(r.getX()));
            r.setY(round(r.getY()));
            //
            r.setR(outsr);
            r.setENTERING(ParamsManager.isInArea(r.getX(),r.getY(),r.getR()));
            //
        }
        return Results;
    }

    public void addResult(){
        try{
            Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

            String sx = requestParameterMap.get("myForm:param-x").replace(',','.');
            String sy = requestParameterMap.get("myForm:param-y").replace(',','.');
            String sr = requestParameterMap.get("myForm:param-r").replace(',','.');

            float x = Float.parseFloat(sx);
            float y = Float.parseFloat(sy);
            float r = Float.parseFloat(sr);

            Result result = new Result();
            try{
                entityManager.getTransaction().begin();

                result.setSEESIONID(sessionID);
                result.setX(x);
                result.setY(y);
                result.setR(r);
                result.setENTERING(ParamsManager.isInArea(x,y,r));

                result = entityManager.merge(result);

                entityManager.getTransaction().commit();
            }catch (Exception e){
                entityManager.getTransaction().rollback();
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Ошибка на этапе addResult");
        }
    }
}
