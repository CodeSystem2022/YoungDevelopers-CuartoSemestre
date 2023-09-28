const modalContainer = document.getElementById("modal-container");
const modalOverlay = document.getElementById("modal-overlay");

const cartBtn = document.getElementById("cart-btn");


const displayCart = () => {
    modalContainer.innerHTML = "";
    modalContainer.style.display = "block";
    modalOverlay.style.display = "block";
    //modal header
    const modalHeader = document.createElement("div");

    const modalClose = document.createElement("div");
    modalClose.innerText = "❌";
    modalClose.className = "modal-close";
    modalHeader.append(modalClose);

    modalClose.addEventListener("click", () => {
        modalContainer.style.display = "None";
        modalOverlay.style.display = ""
    })

    const modalTittle = document.createElement("div");
    modalTittle.innerText = "Cart";
    modalTittle.className = "modal-tittle";
    modalHeader.append(modalTittle);

    modalContainer.append(modalHeader);
};

cartBtn.addEventListener("click", displayCart);