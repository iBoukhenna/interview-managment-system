<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${mailDataDto.subject}</title>
</head>
<body>
<div style="margin-top: 10px">Cher(e), ${mailDataDto.technicalAdvisorName}</div>
<div>Nous tennons à vous informer que la demande de l'entrevue que vous avez accepté est annulé. Pour obtenir de plus amples détails, veuillez cliquer sur le lien suivant : <b>${mailDataDto.linkInterviewDetail}</b></div>
<br/>
<div>Cordialement.</div>
</body>
</html>
