const shopContent = document.getElementById("shopContent"); 
const cart =[]; 

products.forEach((products)=>{
    const content = document.createElement("div"); 
    content.className ="card";
    content.innerHTML = `
    <img src="${products.img}">
    <h3>${products.productName}</h3>
    <p class="price">$ ${products.price} </p>
    `; 
    shopContent.append(content); 

    const buyButton= document.createElement("button"); 
    buyButton.innerText="COMPRAR"; 

    content.append(buyButton); 

    buyButton.addEventListener("click", ()=>{
        const repeat = cart.some((repeatProduct)=> repeatProduct.id === products.id); 
        
        if (repeat) {
            cart.map((prod) => {
                if(prod.id === products.id){
                    prod.quanty++; 
                    displayCartCounter(); 
                }
            }); 
        } else{
            cart.push({
            id:products.id, 
            productName: products.productName, 
            price: products.price, 
            quanty: products.quanty, 
            img: products.img, 
        }); 
        displayCartCounter(); 
        }
    }); 
}); 
