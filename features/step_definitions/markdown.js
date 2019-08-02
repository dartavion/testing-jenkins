const {Given, When, Then} = require('cucumber');
const Selector = require('testcafe').Selector;

Given('I have opened Order Up', async function () {
    await testController.navigateTo('https://usom-client-accepted.apps-np.homedepot.com');
});
