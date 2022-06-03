
window.addEventListener('DOMContentLoaded', ()=>{
    function hideElem(element){
        element.classList.add('hide');
        element.classList.remove('show','pfade');
    }

    function openElement(element){
        element.classList.add('show','pfade');
        element.classList.remove('hide');
    }

    function inputFocus(input,label){
        input.addEventListener('focus',()=>{
            openElement(label);
            input.classList.add('red');
            input.classList.remove('black');
        });


        input.addEventListener('blur',(event)=>{
            if(event.target.value == ''){
                hideElem(label);
                input.classList.add('black');
                input.classList.remove('red');
            }
        });
    }



//Email

    const emailP = document.querySelector('#email-p');
    const emailA = emailP.querySelector('.name__edit a');
    const emailI = document.querySelector('#email-i');

    const newEmailLabel = document.querySelector('#new_email_label');
    const newEmail = document.querySelector('#new_email');

    const confirmNewEmailLabel = document.querySelector('#confirm_new_email_label');
    const confirmNewEmail = document.querySelector('#confirm_new_email');

    const confirmPasswordLabel = document.querySelector('#confirm_password_label');
    const confirmPassword = document.querySelector('#confirm_password');

    const emailCancelButton = document.querySelector('#email_cancel_button');

    emailA.addEventListener('click',()=>{
        openElement(emailI);
        hideElem(emailP);
    });

    emailCancelButton.addEventListener('click',()=>{
        let emailError = document.querySelector('#email-error');
        let passwordError = document.querySelector('#password-error');
        if(emailError != null) emailError.innerHTML ='';
        if(passwordError != null) passwordError.innerHTML ='';
        openElement(emailP);
        hideElem(emailI);
    });

    inputFocus(newEmail,newEmailLabel);
    inputFocus(confirmNewEmail,confirmNewEmailLabel);
    inputFocus(confirmPassword,confirmPasswordLabel);

//Password

    const passwordP = document.querySelector('#password-p');
    const passwordA = passwordP.querySelector('.name__edit a');
    const passwordI = document.querySelector('#password-i');

    const currentPasswordLabel = document.querySelector('#current_password_label');
    const currentPassword = document.querySelector('#current_password');

    const newPasswordLabel = document.querySelector('#new_password_label');
    const newPassword = document.querySelector('#new_password');

    const confirmNewPasswordLabel = document.querySelector('#confirm_new_password_label');
    const confirmNewPassword = document.querySelector('#confirm_new_password');

    const passwordCancelButton = document.querySelector('#password_cancel_button');

    passwordA.addEventListener('click',()=>{
        openElement(passwordI);
        hideElem(passwordP);
    });

    passwordCancelButton.addEventListener('click',()=>{
        openElement(passwordP);
        hideElem(passwordI);
    });

    inputFocus(currentPassword,currentPasswordLabel);
    inputFocus(newPassword,newPasswordLabel);
    inputFocus(confirmNewPassword,confirmNewPasswordLabel);

//Full name

    const fullNameP = document.querySelector('#fullname-p');
    const fullNameA = fullNameP.querySelector('.name__edit a');
    const fullNameI = document.querySelector('#fullname-i');

    const firstNameLabel = document.querySelector('#first_name_label');
    const firstName = document.querySelector('#first_name');


    const fullNameCancelButton = document.querySelector('#fullname_cancel_button');

    fullNameA.addEventListener('click',()=>{
        openElement(fullNameI);
        hideElem(fullNameP);
    });

    fullNameCancelButton.addEventListener('click',(event)=>{
        event.preventDefault();
        openElement(fullNameP);
        hideElem(fullNameI);
    });

    inputFocus(firstName,firstNameLabel);

});

