const shopContent = document.getElementById("shopContent")

products.forEach((products)=>{
    const content = document.createElement("div"); 
    content.innerHTML = `
    <img src="${products.img}">
    <h3>${products.productName}</h3>
    <p>$ ${products.price} </p>
    `; 
    shopContent.append(content); 
}); 