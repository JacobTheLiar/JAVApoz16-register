package pl.sda.register.exception;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2019-10-19 10:31
 * *
 * @className: DuplicatedUserNameException
 * *
 * *
 ******************************************************/
public class DuplicatedUserNameException extends RuntimeException{
    
    public DuplicatedUserNameException(String message){
        super(message);
    }
}
