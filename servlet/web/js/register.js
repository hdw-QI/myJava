/*
 * @Author: hdw-Qi 2799213883@qq.com
 * @Date: 2023-11-21 14:36:28
 * @LastEditors: hdw-Qi 2799213883@qq.com
 * @LastEditTime: 2023-11-21 19:38:16
 * @FilePath: \JavaScript\day01\exercise\js\register.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
focusElement('username','usernameTip')
blurElement('username',checkUsername)

focusElement('password','passwordTip')
blurElement('password',checkPassword)

focusElement('againPassword','againPasswordTip')
blurElement('againPassword',checkAgainPassword)

focusElement('email','emailTip')
blurElement('email',checkEmail)

if(!getIdElement('policy').checked){
    getIdElement('submit').disabled=true
}

getIdElement('policy').onclick=()=>{
    if(!getIdElement('policy').checked){
        getIdElement('submit').disabled=true
    }else{
        getIdElement('submit').disabled=false
    }
}

getIdElement('submit').onclick=()=>{
    let flag=false
    document.querySelectorAll('span').forEach((e)=>{
        if(e.className=='errorTip' || !e.className){
            flag=true
        }
    })
    if(flag){
        alert("表单校验失败")
    }
}


function checkEmail() {
    let email=getIdElement('email').value
    let reg=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
    // console.log(password.length);
    if(reg.test(email)){
        getIdElement('emailTip').innerHTML='邮箱格式正确'
        getIdElement('emailTip').className='correctTip'
    }else{
        getIdElement('emailTip').innerHTML='邮箱格式错误'
        getIdElement('emailTip').className='errorTip'
    }
}

function checkAgainPassword() {
    let password=getIdElement('password').value
    let againPassword=getIdElement('againPassword').value
    // console.log(password.length);
    if(password!=againPassword){
        getIdElement('againPasswordTip').innerHTML='密码不一致。'
        getIdElement('againPasswordTip').className='errorTip'
    }else if(password.length<8 || password.lengt>16){
        getIdElement('againPasswordTip').innerHTML='密码设置错误！'
        getIdElement('againPasswordTip').className='errorTip'
    }else{
        getIdElement('againPasswordTip').innerHTML='密码一致！'
        getIdElement('againPasswordTip').className='correctTip'
    }
}

function checkPassword() {
    let password=getIdElement('password').value
    // console.log(password.length);
    if(password.length<8){
        getIdElement('passwordTip').innerHTML='密码设置错误！长度过小。'
        getIdElement('passwordTip').className='errorTip'
    }else if(password.lengt>16){
        getIdElement('passwordTip').innerHTML='密码设置错误！长度过大。'
        getIdElement('passwordTip').className='errorTip'
    }else{
        getIdElement('passwordTip').innerHTML='密码设置正确！'
        getIdElement('passwordTip').className='correctTip'
    }
}

function checkUsername() {
    let name=getIdElement('username').value
    let reg = /\s+/
    console.log(name.length);
    if(name.lengt>10 || name.length<2){
        getIdElement('usernameTip').innerHTML='用户名长度错误！'
        getIdElement('usernameTip').className='errorTip'
    }else if(reg.test(name)){
        getIdElement('usernameTip').innerHTML='不能有空格'
        getIdElement('usernameTip').className='errorTip'
    }else{
        getIdElement('usernameTip').innerHTML='恭喜您，该用户名还未被注册，您可以使用这个用户名注册！'
        getIdElement('usernameTip').className='correctTip'
    }
}

function getIdElement(element) {
    return document.querySelector(`#${element}`)
}


function focusElement(element,tipElement){
    getIdElement(element).onfocus=()=>{
        getIdElement(tipElement).className='infoTip'
    }
}

function blurElement(element,fun){
    getIdElement(element).onblur=fun
}


