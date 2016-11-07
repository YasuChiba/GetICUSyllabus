/**
 * Created by YasuhiraChiba on 2016/11/06.
 */
public class HtmlDataEntity {


    private String ViewState="";
    private String PreviousPage="";
    private String EventValidation="";


    public String getEventValidation() {
        return EventValidation;
    }

    public String getPreviousPage() {
        return PreviousPage;
    }

    public String getViewState() {
        return ViewState;
    }


    public void setEventValidation(String eventValidation) {
        EventValidation = eventValidation;
    }

    public void setPreviousPage(String previousPage) {
        PreviousPage = previousPage;
    }

    public void setViewState(String viewState) {
        ViewState = viewState;
    }
}
