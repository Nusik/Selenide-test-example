Implement test cases using Selenide and Page Object pattern, use Fragment approach to avoid duplicating of methods on pages (SearchFragment, FilterFragment, BasketFragment).

Test cases:

Basket

1. Add to basket using product page

1) Click on 'Apple' in menu

2) Find product with name 'Apple iPhone 11 128Gb Black', navigate to product page

3) Remember price, add product to basket

4) Go to basket using basket icon in header

5) Verify

- baksket has 1 product

- product name is correct

- product price is correct

- basket total is correct

=================

2. Add to basket using search

1) Search product by name 'Apple iPhone 11'

2) Find product with name 'Apple iPhone 11 128Gb Black', remember price

3) Add product to basket

4) Go to basket using basket icon in header

5) Verify

- baksket has 1 product

- product name is correct

- product price is correct

- basket total is correct

=================

3. Add 2 products to basket

1) Search product by name 'Apple iPhone'

2) Find 2 first products, remember price, remember names

3) Add products to basket

4) Go to basket using basket icon in header

5) Verify

- baksket has 2 product

- product names are correct

- product prices are correct

- basket total is correct

=================

4. Add 2 products to basket from comparison

1) Search product by name 'Apple iPhone'

2) Find 2 first products, remember price, remember names

3) Add products to comparison

4) Click comparison button in header of page

5) Add all products to basket from comparison page (click 'add to basket' button)

6) Go to basket using basket icon in header

7) Verify

- baksket has 2 product

- product names are correct

- product prices are correct

- basket total is correct

=================
Filter

1.Use price filter

1) Click on 'Samsung'in menu

2) Add minimum and maximum prices to filter panel

3) Verify

- all products contain 'samsung' in name

- all product prices are inside of filer range

=================

2.Use memory size filter

1) Click on 'Xiaomi'in menu

2) Add 8gb and 16gb memory sizes to filter

3) Verify

- all products contain 'Xiaomi' in name

- all products on the page contain memory 8 or 16gb

=================

3.Use body material filter

1) Click on 'Google' in menu

2) Add Metal as body material to filter

3) Verify

- all products contain 'Google Pixel' in name

- all products on the page contain Metal as body material

=================

Comparison

1.Compare 2+1 products

1) Click Notebooks/Acer in menu

2) Add first and second laptop to comparison (no navigation to product page). Remember names, prices

3) Click on comparison icon in header

4) Verify

- only 2 products in comparison

- prices and names of products are correct

5) Click 'add new product to comparison'

6) Choose first (remember name, price)

7) Verify

- only 3 products in comparison

- prices and names of products are correct