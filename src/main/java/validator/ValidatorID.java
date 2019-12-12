package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.persistence.criteria.CriteriaBuilder;

@FacesValidator("ValidatorID")
public class ValidatorID implements javax.faces.validator.Validator {
    //@Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        System.out.println(value);
        Double y;
        String id;
        try {
            y = new Double(value.toString());
            id = value.toString();
            System.out.println(id);
            if (!id.matches("[-+]?\\d+")) {
                throw new Exception();
            }
        }
        catch (Exception e) {
            FacesMessage msg =
                    new FacesMessage("Invalid value",
                            "Invalid Value. Only integer numbers can be use.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }
}