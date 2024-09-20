package com.back.Crystal.Model.Help;

public class Result<T> {

    private T Model;
    private String Error;
    private boolean IsError;

    private Result(T Model)
    {
        this.Model = Model;
        IsError = false;
    }

    private Result(String Error)
    {
        this.Error = Error;
        IsError = true;
    }

    public static <T> Result<T> Success(T Model)
    {
        return new Result<T>(Model);
    }

    public static <T> Result<T> Fail(String Error)
    {
        String _Error = "Ошибка: " + Error;
        return new Result<T>(_Error);
    }

    public T getModel() {
        return Model;
    }

    public String getError() {
        return Error;
    }

    public boolean IsError()
    {
        return IsError;
    }
}
