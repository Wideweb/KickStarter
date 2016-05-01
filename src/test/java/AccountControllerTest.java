import com.kickstarter.controllers.AccountController;
import com.kickstarter.controllers.tools.CustomJsonResult;
import com.kickstarter.logic.domain.User;
import com.kickstarter.logic.services.IUserService;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Matchers.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private IUserService userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    private User user;

    @Before
    public void setUp(){
        user = new User();

        Mockito.when(userService.loadUserByUsername(any(String.class))).thenReturn(user);
        Mockito.when(request.getSession()).thenReturn(session);
    }

    @Test
    public void shouldReturnData(){
        CustomJsonResult result = accountController.Logoff(request);

        assertNotNull(result);
        assertNull(result.getErrorMessage());
        assertEquals(result.getData(), true);
    }

    @Test
    public void shouldInvalidateSession(){
        accountController.Logoff(request);
        Mockito.verify(session, Mockito.times(1)).invalidate();
    }
}
