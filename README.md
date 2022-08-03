# product-api

product-api

Criar o BD no Postgres utilizando o script em `product-api\src\db`

Alterar as configs de acesso ao BD no arquivo `product-api\src\main\resources\application.properties`

Executar o projeto utilizando o Maven `mvn spring-boot:run`

Os endpoints estarão acessiveis na url http://localhost:8080/api

Os métodos criados foram os seguintes:

```java
createProduct(@RequestBody ProductDto productDto)-RequestMethod.POST

        getProducts(FilterDto filterDto)-RequestMethod.GET

        getProductById(@PathVariable(value = "productId") Long productId)-RequestMethod.GET

        updateProduct(@PathVariable(value = "productId") Long productId,@RequestBody ProductDto productDto)-RequestMethod.PUT

        disableProductById(@PathVariable(value = "productId") Long productId)-RequestMethod.DELETE

        activateProductById(@PathVariable(value = "productId") Long productId)-RequestMethod.PUT
```