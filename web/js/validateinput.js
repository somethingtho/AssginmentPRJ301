/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function testNumber(input) {
    const pattern = /^\d+$/;
    return pattern.test(input);
}

function isValidEmail(email) {
  // Regular expression pattern for a valid email address
  const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return pattern.test(email);
}



function isValidUsername(username) {
  // Regular expression pattern for a valid username
  const pattern = /^[a-zA-Z0-9_]{3,16}$/;
  return pattern.test(username);
}



function isValidPassword(password) {
  // Regular expression pattern for a strong password
  const pattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/;
  return pattern.test(password);
}



function isValidCustomerName(customerName) {
  // Regular expression pattern for a valid customer name
  const pattern = /^[a-zA-Z\s]*$/;
  return pattern.test(customerName);
}

function isValidPhoneNumber(phoneNumber) {
  // Regular expression pattern for a valid phone number
  const pattern = /^\d{10}$/;
  return pattern.test(phoneNumber);
}