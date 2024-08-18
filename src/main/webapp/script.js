function checkPin() {
    const pin = document.getElementById('pin').value;
    fetch('/validate-pin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `pin=${pin}`
    })

    .then(response => response.text())
    .then(data => {

        if (data === "Valid") {
            document.getElementById('screen').innerText = "PIN Validated";
            document.getElementById('actions').style.display = 'block';
        } else {
            document.getElementById('screen').innerText = "Invalid PIN";
        }
    });
}

function checkBalance() {
    const pin = document.getElementById('pin').value;
    fetch('/balance', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `pin=${pin}`
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById('screen').innerText = `Balance: $${data}`;
    });
}

function withdraw() {
    const pin = document.getElementById('pin').value;
    const amount = document.getElementById('amount').value;
    fetch('/withdraw', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `pin=${pin}&amount=${amount}`
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById('screen').innerText = data;
    });
}
