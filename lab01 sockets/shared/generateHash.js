const crypto = require('crypto');

function generateHashedId(clientId) {
    const hash = crypto.createHash('sha256').update(clientId).digest('hex');
    return parseInt(hash.substring(0, 8), 16);
}



module.exports = generateHashedId;