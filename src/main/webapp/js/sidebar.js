window.addEventListener('DOMContentLoaded', ()=>{

    function hideElem(element){
        element.classList.add('phide');
        element.classList.remove('pshow','pfade');
    }

    function openElement(element){
        element.classList.add('pshow','pfade');
        element.classList.remove('phide');
    }

    const block = document.querySelector('#bag_block');
    const sideBar = document.querySelector('#sideBar');
    let isClicked = false;
    block.addEventListener('click',()=>{
        console.log(123);
        if(isClicked){
            hideElem(sideBar);
            isClicked = false;
        }else {
            openElement(sideBar)
            isClicked = true;
        }
    })
});