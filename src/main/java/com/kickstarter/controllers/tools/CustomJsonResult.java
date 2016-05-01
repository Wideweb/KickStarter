package com.kickstarter.controllers.tools;

import com.kickstarter.extentions.Action;
import com.kickstarter.extentions.Function;

public class CustomJsonResult<TEntity> {

    private String errorMessage;
    private TEntity data;
    private Boolean success;

    public static <TEntity> CustomJsonResult TryGetJsonResult(Function<TEntity> function)
    {
        CustomJsonResult result = new CustomJsonResult();
        try
        {
            result.setData(function.getResult());
            result.setSuccess(true);
        }
        catch (Exception e)
        {
            result.setErrorMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    public static CustomJsonResult TryExecute(Action function)
    {
        CustomJsonResult result = new CustomJsonResult();
        try
        {
            function.run();
            result.setData(true);
            result.setSuccess(true);
        }
        catch (Exception e)
        {
            result.setErrorMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }


    public TEntity getData() {
        return data;
    }

    public void setData(TEntity data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
