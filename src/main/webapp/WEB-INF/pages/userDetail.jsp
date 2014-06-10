    <!DOCTYPE html>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html lang=en>
        <head>
        <meta charset=UTF-8>
        <title>Example of Twitter Bootstrap 3 Supported Form Controls</title>
        <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"
        rel="stylesheet">
        <link rel=stylesheet
        href=http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

        <style type=text/css>
        h1{
        margin: 30px 0;
        padding: 0 200px 15px 0;
        border-bottom: 1px solid #E5E5E5;
        }
        .bs-example{
        margin: 20px;
        }

        </style>
        </head>
        <body>
        <div class=bs-example>
        <h1>Sign Up</h1>
        <form class=form-horizontal>
        <div class=form-group>
        <label class=control-label col-xs-3 for=inputEmail>Email:</label>
        <div class=col-xs-9>
        <input type=email class=form-control id=inputEmail
        placeholder=Email>
        </div>
        </div>
        <div class=form-group>
        <label class=control-label col-xs-3
        for=inputPassword>Password:</label>
        <div class=col-xs-9>
        <input type=password class=form-control id=inputPassword
        placeholder=Password>
        </div>
        </div>
        <div class=form-group>
        <label class=control-label col-xs-3 for=confirmPassword>Confirm
        Password:</label>
        <div class=col-xs-9>
        <input type=password class=form-control
        id=confirmPassword placeholder=Confirm Password>
        </div>
        </div>
        <div class=form-group>
        <label class=control-label col-xs-3 for=firstName>First
        Name:</label>
        <div class=col-xs-9>
        <input type=text class=form-control id=firstName
        placeholder=First Name>
        </div>
        </div>
        <div class=form-group>
        <label class=control-label col-xs-3 for=lastName>Last Name:</label>
        <div class=col-xs-9>
        <input type=text class=form-control id=lastName
        placeholder=Last Name>
        </div>
        </div>
        <div class=form-group>
        <label class=control-label col-xs-3 for=phoneNumber>Phone:</label>
        <div class=col-xs-9>
        <input type=tel class=form-control id=phoneNumber
        placeholder=Phone Number>
        </div>
        </div>
        <div class=form-group>
        <label class=control-label col-xs-3>Date of Birth:</label>
        <div class=col-xs-3>
        <select class=form-control>
        <option>Date</option>
        </select>
        </div>
        <div class=col-xs-3>
        <select class=form-control>
        <option>Month</option>
        </select>
        </div>
        <div class=col-xs-3>
        <select class=form-control>
        <option>Year</option>
        </select>
        </div>
        </div>
        <div class=form-group>
        <label class=control-label col-xs-3
        for=postalAddress>Address:</label>
        <div class=col-xs-9>
        <textarea rows=3 class=form-control id=postalAddress
        placeholder=Postal Address></textarea>
        </div>
        </div>
        <div class=form-group>
        <label class=control-label col-xs-3 for=ZipCode>Zip Code:</label>
        <div class=col-xs-9>
        <input type=number class=form-control id=ZipCode
        placeholder=Zip Code>
        </div>
        </div>
        <div class=form-group>
        <label class=control-label col-xs-3>Gender:</label>
        <div class=col-xs-2>
        <label class=radio-inline>
        <input type=radio name=genderRadios value=male> Male
        </label>
        </div>
        <div class=col-xs-2>
        <label class=radio-inline>
        <input type=radio name=genderRadios value=female> Female
        </label>
        </div>
        </div>
        <div class=form-group>
        <div class=col-xs-offset-3 col-xs-9>
        <label class=checkbox-inline>
        <input type=checkbox value=news> Send me latest news and updates.
        </label>
        </div>
        </div>
        <div class=form-group>
        <div class=col-xs-offset-3 col-xs-9>
        <label class=checkbox-inline>
        <input type=checkbox value=agree> I agree to the <a
        href=#>Terms and Conditions</a>.
        </label>
        </div>
        </div>
        <br>
        <div class=form-group>
        <div class=col-xs-offset-3 col-xs-9>
        <input type=submit class=btn btn-primary value=Submit>
        <input type=reset class=btn btn-default value=Reset>
        </div>
        </div>
        </form>
        </div>
        </body>
        </html>