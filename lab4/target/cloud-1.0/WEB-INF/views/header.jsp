<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <div class="menu">
        <a href="/">
            <div class="title">DNet Tickets</div>
        </a>
        <span>
            <c:if test="${manager}">
                <a href="/manager">
                    <span class="manager">
                        <img src="/images/receipt.svg" height="24">
                    </span>
                </a>
            </c:if>
            <a href="/cart">
                <span class="cart">
                    <img src="/images/shopping_cart.svg" height="24">
                    <c:if test="${cartLength != \"0\"}">
                        <div class="cartBadge">${cartLength}</div>
                    </c:if>
                </span>
            </a>
            <a href="/account">
                <span class="account">
                    <img src="/images/account.svg" height="24">
                </span>
            </a>
            <a href="/logout">
                <span class="logout">
                    <img src="/images/logout.svg" height="24">
                </span>
            </a>
        </span>
    </div>
</header>
