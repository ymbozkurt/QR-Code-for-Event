

//const nodemailer = require('nodemailer');
import hbs from 'nodemailer-express-handlebars'
import path from 'path'
import nodemailer from 'nodemailer'
import QRCode from 'qrcode'
import XLSX from 'xlsx'


let transporter = nodemailer.createTransport({
    service: 'gmail',
    auth: {
        user: 'your_mail_address',
        pass: 'your_password' // (Not mail password) search https://stackoverflow.com/questions/45478293/username-and-password-not-accepted-when-using-nodemailer for help.
    }
});

transporter.verify(function (error, success) {

    if (error) throw error;

    console.log('Successfully connected to e-mail');

});




const excelFilePath = './your_excell_name.xlsx';

// Excel dosyasını oku
const workbook = XLSX.readFile(excelFilePath);

// İlk sayfadaki (worksheet) verileri al
const sheetName = workbook.SheetNames[0];
const worksheet = workbook.Sheets[sheetName];

// Verileri obje olarak çek
const data = XLSX.utils.sheet_to_json(worksheet, { header: 1 });


const handlebarOptions = {
    viewEngine: {
        partialsDir: path.resolve('./views/'),
        defaultLayout: false,
    },
    viewPath: path.resolve('./views/'),
};

// use a template file with nodemailer
transporter.use('compile', hbs(handlebarOptions))



var qrImgPathOfUser


function wait(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}


for (let i = 1; i < 561; i++) {
    qrImgPathOfUser = "./qrimages/qr" + i + ".jpg"

    QRCode.toFile(qrImgPathOfUser, i+"-"+data[i][1]+ "-" + data[i][2], (err) => {
        console.log("QR created for: " + data[i][1] + " " + data[i][2])
        if (err) throw err;
    });
}

var info
async function main() {
    for (let i = 1; i < data.length; i++) {
        qrImgPathOfUser = "./qrimages/qr" + i + ".jpg"
        info = {
            from: '"Organizator Name" <mailaddress@gmail.com>', // sender address
            template: "email", // the name of the template file, i.e., email.handlebars
            to: data[i][3],
            subject: `Welcome To Our Event ${data[i][1]}!`,
            attachments: [{
                filename: "qr" + i + ".jpg",
                path: qrImgPathOfUser,
                cid: "qr"
            },
            {
                filename: "header.png",
                path: "./htmlimages/header.png",
                cid: "header"
            }
            ],
            context: {
                name: data[i][1],
            },
        };
        try {
            await transporter.sendMail(info, function (error, info) {
                if (error) {
                    console.error(error)
                }
                else {
                    console.log("Mail sent to "+data[i][1])
                }
            });
        } catch (error) {

        }
        await wait(2000)

    }


}


main()