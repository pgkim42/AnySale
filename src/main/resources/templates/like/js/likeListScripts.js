fetch(`/likeList/list?memberId=${memberId}`)
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log(data);  // 데이터를 콘솔에 출력하여 확인
        const productList = document.querySelector('.product-list');
        productList.innerHTML = ''; // 기존 내용을 초기화
        data.forEach(item => {
            const productItem = document.createElement('div');
            productItem.classList.add('product-item');
            productItem.innerHTML = `
                <img src="assets/item/johwan.jpg" alt="상품 이미지">
                <div class="product-info">
                    <div class="div-temp1">
                        <h2>${item.itemCode}</h2>
                        <p>상품 설명</p>
                        <span class="product-price">가격 정보</span>
                    </div>
                    <div class="div-temp2">
                        <button class="like-button" onclick="removeLikeList('${item.itemCode}')">
                            <img src="assets/icon/heart.gif" alt="찜하기">
                        </button>
                    </div>
                </div>
            `;
            productList.appendChild(productItem);
        });
    })
    .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
    });
//