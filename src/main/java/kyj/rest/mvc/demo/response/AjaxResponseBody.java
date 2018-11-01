package kyj.rest.mvc.demo.response;

import java.util.List;

import kyj.rest.mvc.demo.request.PostRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AjaxResponseBody {
	String msg;
    Boolean result;
}
