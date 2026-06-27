# 📌 Problem Statement

You are given **two lists**:

---

## **BillingAddressDTO**

```java
class BillingAddressDTO {
    Long billingAddressId;
    String cityName;
    String pinCode;
    Long customerId;
}
```

---

## **ShippingAddressDTO**

```java
class ShippingAddressDTO {
    Long billingAddressId;
    Long shippingAddressId;
    String pinCode;
}
```

---

# 🎯 Task

Find **all cities** that have **500 or more customers** who satisfy this condition:

> The customer must have **at least two shipping addresses** whose **pinCode matches the pinCode of their billing address**.

### In other words:

- Join shipping → billing using `billingAddressId`
- For each billing address (customer):
  - Count shipping addresses with **same pinCode** as billing
  - If count ≥ 2 → customer qualifies
- Group qualifying customers by **cityName**
- Return cities with **count ≥ 500**

---

# 📦 Example Input

## **Billing Addresses**

```json
[
  { "billingAddressId": 1, "cityName": "Mumbai", "pinCode": "400001", "customerId": 101 },
  { "billingAddressId": 2, "cityName": "Mumbai", "pinCode": "400001", "customerId": 102 },
  { "billingAddressId": 3, "cityName": "Delhi",  "pinCode": "110011", "customerId": 201 },
  { "billingAddressId": 4, "cityName": "Delhi",  "pinCode": "110011", "customerId": 202 }
]
```

---

## **Shipping Addresses**

```json
[
  { "billingAddressId": 1, "shippingAddressId": 11, "pinCode": "400001" },
  { "billingAddressId": 1, "shippingAddressId": 12, "pinCode": "400001" },
  { "billingAddressId": 1, "shippingAddressId": 13, "pinCode": "400002" },

  { "billingAddressId": 2, "shippingAddressId": 21, "pinCode": "400001" },
  { "billingAddressId": 2, "shippingAddressId": 22, "pinCode": "400003" },

  { "billingAddressId": 3, "shippingAddressId": 31, "pinCode": "110011" },
  { "billingAddressId": 3, "shippingAddressId": 32, "pinCode": "110011" },

  { "billingAddressId": 4, "shippingAddressId": 41, "pinCode": "110012" }
]
```

---

# 🔍 Qualification Logic

### **Customer 101 (billingAddressId = 1, Mumbai)**  
Shipping pinCodes: 400001, 400001, 400002  
Matches billing pinCode (400001): **2** → ✔ qualifies

### **Customer 102 (billingAddressId = 2, Mumbai)**  
Shipping pinCodes: 400001, 400003  
Matches billing pinCode (400001): **1** → ✘ does NOT qualify

### **Customer 201 (billingAddressId = 3, Delhi)**  
Shipping pinCodes: 110011, 110011  
Matches billing pinCode (110011): **2** → ✔ qualifies

### **Customer 202 (billingAddressId = 4, Delhi)**  
Shipping pinCodes: 110012  
Matches billing pinCode (110011): **0** → ✘ does NOT qualify

---

# 📊 City Counts

| City   | Qualifying Customers |
|--------|------------------------|
| Mumbai | 1                      |
| Delhi  | 1                      |

---

# ✅ Expected Output (for this example)

```json
{}
```

Because no city has **≥ 500** qualifying customers.

---

# 🏆 Example Output (if counts were higher)

```json
{
  "Mumbai": 512,
  "Delhi": 503
}